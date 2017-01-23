using System;
using System.Security;
using NUnit.Framework;

namespace OneTimePassword.Test.UnitTests
{
    [TestFixture]
    public class OneTimePasswordGeneratorFactoryTests
    {
        [Test]
        public void FactoryCreatesExpectedGenerator()
        {
            // Arrange
            var configuration = new OneTimePasswordGenerationConfiguration(new DateTime(1900), TimeSpan.FromSeconds(2), 4);
            var secretKey = new SecureString();

            // Act
            var factoryUnderTest = new OneTimePasswordGeneratorFactory(configuration);
            var generator = factoryUnderTest.CreateGenerator(secretKey);

            // Assert
            Assert.AreEqual(configuration, factoryUnderTest.Configuration);
            Assert.IsInstanceOf(typeof(OneTimePasswordGenerator), generator);
        }
    }
}