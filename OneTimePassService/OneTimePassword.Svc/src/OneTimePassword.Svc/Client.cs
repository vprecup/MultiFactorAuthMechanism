using System;
using System.Security;

namespace OneTimePassword.Svc
{
    public class Client
    {
        private IOneTimePasswordGenerator passwordGenerator; 

        public Client(SecureString sharedSecretKey, IOneTimePasswordGeneratorFactory passwordGeneratorFactory)
        {
            this.passwordGenerator = passwordGeneratorFactory.CreateGenerator(sharedSecretKey);
        }

        public string GeneratePassword(string userId)
        {
            return this.passwordGenerator.GenerateOneTimePassword(userId, DateTime.UtcNow);
        }
    }
}