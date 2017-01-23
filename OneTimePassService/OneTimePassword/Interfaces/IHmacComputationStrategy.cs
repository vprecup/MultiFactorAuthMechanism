using System.Security;

namespace OneTimePassword
{
    public interface IHmacComputationStrategy
    {
        byte[] ComputeHmac(SecureString secureKey, string userId, long timeCounter);
    }
}