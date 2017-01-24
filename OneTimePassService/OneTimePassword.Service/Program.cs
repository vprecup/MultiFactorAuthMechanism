using System;
using System.Security;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.DependencyInjection;

namespace OneTimePassword.Service
{
    public class Program
    {
        private const string SecureToken = "ewqEY43fDEWR";
        public static Client TokenClient { get; private set; }
        public static Server TokenServer { get; private set; }

        public static void Main(string[] args)
        {
            // Build the oneTimeToken Client and Server.
            var config = new OneTimePasswordGenerationConfiguration(new DateTime(2000,1,1), TimeSpan.FromSeconds(30), 6);
            var factory = new OneTimePasswordGeneratorFactory(config);
            
            var secureKey = ReadSecureKey(SecureToken);
            TokenClient = new Client(secureKey, factory);
            TokenServer = new Server(secureKey, factory);

            var host = new WebHostBuilder().UseKestrel().UseStartup<Program>().Build();
            host.Run();
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddMvc();
        }

        public void Configure(IApplicationBuilder app)
        {
            app.UseMvcWithDefaultRoute();
        }

        private static SecureString ReadSecureKey(string key)
        {
            var secureKey = new SecureString();
            for (int i=0; i < key.Length; i++)
            {
                secureKey.AppendChar(key[i]);
            }
            return secureKey;
        }
    }
}

