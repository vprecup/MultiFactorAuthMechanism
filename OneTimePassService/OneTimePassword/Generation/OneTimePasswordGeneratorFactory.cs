using System.Security;

namespace OneTimePassword
{
    public class OneTimePasswordGeneratorFactory : IOneTimePasswordGeneratorFactory
    {
        public OneTimePasswordGenerationConfiguration Configuration { get; private set; }

        public OneTimePasswordGeneratorFactory(OneTimePasswordGenerationConfiguration configuration)
        {
            this.Configuration = configuration;
        }

        public OneTimePasswordGenerator CreateGenerator(SecureString sharedSecretKey)
        {
            var timeCounterProvider = new TimeCounterProvider(this.Configuration.TimeBasedPasswordGenerationEpoch, this.Configuration.TimeCounterInterval);
            var hmacComputationStrategy = new HmacSha1ComputationStrategy();
            var passwordSelectionStrategy = new PasswordSelectionStrategy(this.Configuration.PasswordLength);
            return new OneTimePasswordGenerator(timeCounterProvider, hmacComputationStrategy, passwordSelectionStrategy, sharedSecretKey);
        }
    }
}