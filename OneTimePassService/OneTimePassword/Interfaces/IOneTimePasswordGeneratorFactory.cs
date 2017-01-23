using System.Security;

namespace OneTimePassword
{
    public interface IOneTimePasswordGeneratorFactory
    {
        OneTimePasswordGenerationConfiguration Configuration { get; }
        OneTimePasswordGenerator CreateGenerator(SecureString sharedSecretKey);
    }
}