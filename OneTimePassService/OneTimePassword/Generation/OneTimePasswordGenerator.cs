using System;
using System.Security;

namespace OneTimePassword
{
    public class OneTimePasswordGenerator : IOneTimePasswordGenerator
    {
        private ITimeCounterProvider timeCounterProvider; 
        private IHmacComputationStrategy hmacComputationStrategy; 
        private IPasswordSelectionStrategy passwordSelectionStrategy;
        private SecureString sharedSecurityKey;

        public OneTimePasswordGenerator(
            ITimeCounterProvider timeCounterProvider, 
            IHmacComputationStrategy hmacComputationStrategy, 
            IPasswordSelectionStrategy passwordSelectionStrategy,
            SecureString sharedSecurityKey)
        {
            this.timeCounterProvider = timeCounterProvider;
            this.hmacComputationStrategy = hmacComputationStrategy;
            this.passwordSelectionStrategy = passwordSelectionStrategy;
            this.sharedSecurityKey = sharedSecurityKey;
        }

        public string GenerateOneTimePassword(string userId, DateTime generationDateTime)
        {
            // 1. Get the time counter of the password generation date time since the epoch
            long timeCounter = this.timeCounterProvider.GetTimeCounterUntil(generationDateTime);

            // 2. Compute the hash of the userId + the time counter using the shared security key.
            byte[] computedHash = this.hmacComputationStrategy.ComputeHmac(this.sharedSecurityKey, userId, timeCounter);

            // 3. Select the digits from the computed HMAC.
            return this.passwordSelectionStrategy.GetPassword(computedHash);
        }
    }
}