using System;

namespace OneTimePassword
{
    public class TimeCounterProvider : ITimeCounterProvider
    {
        private DateTime epoch;
        private TimeSpan expirationTimeInterval;

        public TimeCounterProvider(DateTime epoch, TimeSpan expirationTimeInterval)
        {
            this.epoch = epoch;
            this.expirationTimeInterval = expirationTimeInterval;
        }

        public long GetTimeCounterUntil(DateTime time)
        {
            if (time < epoch)
            {
                throw new ArgumentException("The provided time is invalid.");
            }

            if (time >= epoch && time < epoch + expirationTimeInterval)
            {
                return 0;
            }

            var delta = time - epoch;
            return (long)(delta.TotalSeconds / expirationTimeInterval.TotalSeconds + 1);
        }
    }
}