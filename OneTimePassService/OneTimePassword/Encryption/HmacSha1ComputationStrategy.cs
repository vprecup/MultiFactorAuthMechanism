using System;
using System.IO;
using System.Runtime.InteropServices;
using System.Security;
using System.Security.Cryptography;
using System.Text;

namespace OneTimePassword
{
    public class HmacSha1ComputationStrategy : IHmacComputationStrategy
    {
        public byte[] ComputeHmac(SecureString secureKey, string userId, long timeCounter)
        {
            HMACSHA1 hmacAlgorithm = new HMACSHA1();
            // Should need to use the Marshaling capability for SecureString here... but there is a .NET Core limitation currently
            // Using a workaround instead (GetHashCode) for demo purposes.
            hmacAlgorithm.Key = secureKey.GetHashCode().ToByteArray();
            
            var hashablePayload = (userId + timeCounter.ToString()).ToByteArray();
            var hash = hmacAlgorithm.ComputeHash(hashablePayload);
            return hash;
        }
    }
}