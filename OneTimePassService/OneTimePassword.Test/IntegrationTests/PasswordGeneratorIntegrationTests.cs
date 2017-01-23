using System;
using System.Security;
using NUnit.Framework;

namespace OneTimePassword.Test.IntegrationTests
{
    [TestFixture]
    public class PasswordGeneratorIntegrationTests
    {
        [Test]
        public void GeneratorGeneratesSamePasswordForCloseDateTimeButDifferentForFarDates()
        {
            // Arrange
            var epoch = new DateTime(1990, 1, 1);
            var timeCounterInterval = TimeSpan.FromSeconds(30);
            uint passwordLength = 6;
            var sharedKey = new SecureString();
            sharedKey.AppendString(new char[]{'k', 'e', 'y'});

            var passGenerationConfiguration = new OneTimePasswordGenerationConfiguration(epoch, timeCounterInterval, passwordLength);
            var passwordGeneratorUnderTest = new OneTimePasswordGeneratorFactory(passGenerationConfiguration).CreateGenerator(sharedKey);

            // Act
            var pass1 = passwordGeneratorUnderTest.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 12, 32));    
            var pass2 = passwordGeneratorUnderTest.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 12, 40));
            var passDifferent = passwordGeneratorUnderTest.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 13, 0));
            var passDifferent2 = passwordGeneratorUnderTest.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 12, 13, 0));

            // Assert
            Assert.AreEqual(pass2, pass1);
            Assert.AreNotEqual(pass1, passDifferent);
            Assert.AreNotEqual(pass1, passDifferent2);
            Assert.AreNotEqual(passDifferent, passDifferent2);
        }

        [Test]
        public void GeneratorGeneratesDifferentPasswordsForDifferentKeys()
        {
            // Arrange
            var epoch = new DateTime(1990, 1, 1);
            var timeCounterInterval = TimeSpan.FromSeconds(30);
            uint passwordLength = 6;
            var sharedKey = new SecureString();
            sharedKey.AppendString(new char[]{'k', 'e', 'y'});

            var passGenerationConfiguration = new OneTimePasswordGenerationConfiguration(epoch, timeCounterInterval, passwordLength);
            var factory = new OneTimePasswordGeneratorFactory(passGenerationConfiguration);
            var passwordGeneratorUnderTest1 = factory.CreateGenerator(sharedKey);

            var sharedKey2 = new SecureString();
            sharedKey2.AppendString(new char[]{'n', 'f', '@', 'w', 'A', 'y', 'K', '2'});
            var passwordGeneratorUnderTest2 = factory.CreateGenerator(sharedKey2);

            // Act
            var pass1 = passwordGeneratorUnderTest1.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 12, 32));    
            var pass2 = passwordGeneratorUnderTest2.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 12, 32));
            
            // Assert
            Assert.AreNotEqual(pass2, pass1);
        }

        [Test]
        public void GeneratorGeneratesDifferentPasswordsForDifferentUserIds()
        {
            // Arrange
            var epoch = new DateTime(1990, 1, 1);
            var timeCounterInterval = TimeSpan.FromSeconds(30);
            uint passwordLength = 6;
            var sharedKey = new SecureString();
            sharedKey.AppendString(new char[]{'k', 'e', 'y'});

            var passGenerationConfiguration = new OneTimePasswordGenerationConfiguration(epoch, timeCounterInterval, passwordLength);
            var factory = new OneTimePasswordGeneratorFactory(passGenerationConfiguration);
            var passwordGeneratorUnderTest = factory.CreateGenerator(sharedKey);

            // Act
            var pass1 = passwordGeneratorUnderTest.GenerateOneTimePassword("userId1243", new DateTime(2016, 10, 25, 0, 12, 32));    
            var pass2 = passwordGeneratorUnderTest.GenerateOneTimePassword("user2Id1243", new DateTime(2016, 10, 25, 0, 12, 32));
            
            // Assert
            Assert.AreNotEqual(pass2, pass1);
        }
    }
}