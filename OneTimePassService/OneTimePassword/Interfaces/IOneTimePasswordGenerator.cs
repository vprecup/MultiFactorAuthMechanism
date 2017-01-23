using System;

namespace OneTimePassword
{
    public interface IOneTimePasswordGenerator
    {
        string GenerateOneTimePassword(string userId, DateTime generationDateTime);
    }
}