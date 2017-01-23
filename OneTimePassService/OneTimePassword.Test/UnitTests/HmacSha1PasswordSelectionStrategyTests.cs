using NUnit.Framework;

namespace OneTimePassword.Test.UnitTests
{
    [TestFixture]
    public class HmacSha1PasswordSelectionStrategyTests
    {
        private const uint TestPasswordLength = 6;

        [Test]
        public void StrategyReturnsExpectedPasswordForUsualSizeArray()
        {
            byte[] testCode = {234, 12, 43, 42, 34, 123, 11, 54, 19, 10, 54, 65, 12, 14, 15, 16, 17, 18, 19, 20};
            var strategyUnderTest = new HmacSha1PasswordSelectionStrategy(TestPasswordLength);
            Assert.AreEqual("722082", strategyUnderTest.GetPassword(testCode));
        }

        [Test]
        public void StrategyReturnsZeroPaddedPasswordForValueWithNotEnoughDigits()
        {
            byte[] testCode = {234, 12, 43, 42, 0, 1, 0, 0, 19, 10, 54, 65, 12, 14, 15, 16, 17, 18, 19, 20};
            var strategyUnderTest = new HmacSha1PasswordSelectionStrategy(TestPasswordLength);
            Assert.AreEqual("000256", strategyUnderTest.GetPassword(testCode));
        }
    }
}