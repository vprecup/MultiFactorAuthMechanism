using System;

namespace OneTimePassword
{
    public interface ITimeCounterProvider
    {
        long GetTimeCounterUntil(DateTime time);
    }
}