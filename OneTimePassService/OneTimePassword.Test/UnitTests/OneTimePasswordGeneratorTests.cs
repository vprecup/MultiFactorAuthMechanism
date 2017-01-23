using System;
using System.Security;
using Moq;
using NUnit.Framework;

namespace OneTimePassword.Test.UnitTests
{
    [TestFixture]
    public class OneTimePasswordGeneratorTests
    {
        private static DateTime TestDateTime = new DateTime(2016, 10, 25, 15, 15, 15);
        private const string TestUserId = "TestUser";
        [Test]
        public void GeneratorCallsTimeCounterProviderOnce()
        {
            // Arrange
            var timeCounterProviderMock = new Mock<ITimeCounterProvider>();
            var generatorUnderTest = new OneTimePasswordGenerator(
                timeCounterProviderMock.Object, 
                new Mock<IHmacComputationStrategy>().Object,
                new Mock<IPasswordSelectionStrategy>().Object,
                new SecureString());

            // Act
            generatorUnderTest.GenerateOneTimePassword(TestUserId, TestDateTime);

            // Assert
            timeCounterProviderMock.Verify(tcp => tcp.GetTimeCounterUntil(TestDateTime), Times.Once);
        }

        [Test]
        public void GeneratorCallsHmacAlgorithmOnceWithProviderInput()
        {
            // Arrange
            var testTimeCounter = 10;
            var testSecureString = new SecureString();
            var timeCounterProviderStub = new Mock<ITimeCounterProvider>();
            timeCounterProviderStub.Setup(tcp => tcp.GetTimeCounterUntil(TestDateTime)).Returns(testTimeCounter);
            var hmacComputationStrategyMock = new Mock<IHmacComputationStrategy>();
            var generatorUnderTest = new OneTimePasswordGenerator(
                timeCounterProviderStub.Object, 
                hmacComputationStrategyMock.Object,
                new Mock<IPasswordSelectionStrategy>().Object,
                testSecureString);

            // Act
            generatorUnderTest.GenerateOneTimePassword(TestUserId, TestDateTime);

            // Assert
            hmacComputationStrategyMock.Verify(hcs => hcs.ComputeHmac(testSecureString, TestUserId, testTimeCounter), Times.Once);
        }

        [Test]
        public void GeneratorSelectsPasswordDigitsFromTheHmacPayload()
        {
            // Arrange
            var testTimeCounter = 10;
            var testSecureString = new SecureString();
            var hmacPayload = new byte[]{234, 12, 43, 42, 34, 123, 11, 54, 19, 10, 54, 65, 12, 14, 15, 16, 17, 18, 19, 20};
            var timeCounterProviderStub = new Mock<ITimeCounterProvider>();
            timeCounterProviderStub.Setup(tcp => tcp.GetTimeCounterUntil(TestDateTime)).Returns(testTimeCounter);
            var hmacComputationStrategyStub = new Mock<IHmacComputationStrategy>();
            hmacComputationStrategyStub.Setup(hcs => hcs.ComputeHmac(testSecureString, TestUserId, testTimeCounter))
                .Returns(hmacPayload);
            var passwordSelectionStrategyMock = new Mock<IPasswordSelectionStrategy>();
            var generatorUnderTest = new OneTimePasswordGenerator(
                timeCounterProviderStub.Object, 
                hmacComputationStrategyStub.Object,
                passwordSelectionStrategyMock.Object,
                testSecureString);

            // Act
            generatorUnderTest.GenerateOneTimePassword(TestUserId, TestDateTime);

            // Assert
            passwordSelectionStrategyMock.Verify(pss => pss.GetPassword(hmacPayload), Times.Once);
        }
    }
}