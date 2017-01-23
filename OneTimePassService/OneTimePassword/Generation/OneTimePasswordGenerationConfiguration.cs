using System;

namespace OneTimePassword
{
    public class OneTimePasswordGenerationConfiguration
    {
        public DateTime TimeBasedPasswordGenerationEpoch { get; private set; }

        public TimeSpan TimeCounterInterval { get; private set; }

        public uint PasswordLength { get; private set; }

        public OneTimePasswordGenerationConfiguration(DateTime epoch, TimeSpan timeCounterInterval, uint passwordLength)
        {
            this.TimeBasedPasswordGenerationEpoch = epoch;
            this.TimeCounterInterval = timeCounterInterval;
            this.PasswordLength = passwordLength;
        }   
    }
}