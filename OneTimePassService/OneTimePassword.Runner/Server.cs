using System;
using System.Security;
using System.Runtime.InteropServices;

namespace OneTimePassword.Runner
{
    public class Server
    {
        private IOneTimePasswordGenerator passwordGenerator;
        private OneTimePasswordGenerationConfiguration configuration;

        public Server(SecureString sharedSecretKey, IOneTimePasswordGeneratorFactory passwordGeneratorFactory)
        {
            this.passwordGenerator = passwordGeneratorFactory.CreateGenerator(sharedSecretKey);
            this.configuration = passwordGeneratorFactory.Configuration;
        }

        public bool ValidatePassword(string password, string userId)
        {
            var utcNow = DateTime.UtcNow;
            var expectedPassword = this.passwordGenerator.GenerateOneTimePassword(userId, utcNow);
            if (password == expectedPassword)
            {
                return true;
            }

            // Allow for clock skews on the server side by checking the 2 surrounding intervals as well.
            expectedPassword = this.passwordGenerator.GenerateOneTimePassword(userId, utcNow + this.configuration.TimeCounterInterval);
            if (password == expectedPassword)
            {
                return true;
            }

            expectedPassword = this.passwordGenerator.GenerateOneTimePassword(userId, utcNow - this.configuration.TimeCounterInterval);
            if (password == expectedPassword)
            {
                return true;
            }

            return false;
        }
    }
}