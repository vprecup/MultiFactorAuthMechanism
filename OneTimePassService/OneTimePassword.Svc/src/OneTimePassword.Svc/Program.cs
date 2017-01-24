using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Builder;
using System.Security;

namespace OneTimePassword.Svc
{
    public class Program
    {
        private const string SecureToken = "ewqEY43fDEWR";
        public static Client TokenClient { get; private set; }
        public static Server TokenServer { get; private set; }

        public static void Main(string[] args)
        {
            // Build the oneTimeToken Client and Server.
            var config = new OneTimePasswordGenerationConfiguration(new DateTime(2000, 1, 1), TimeSpan.FromSeconds(30), 6);
            var factory = new OneTimePasswordGeneratorFactory(config);

            var secureKey = ReadSecureKey(SecureToken);
            TokenClient = new Client(secureKey, factory);
            TokenServer = new Server(secureKey, factory);
            var host = new WebHostBuilder()
                .UseKestrel()
                .UseContentRoot(Directory.GetCurrentDirectory())
                .UseIISIntegration()
                .UseStartup<Startup>()
                .Build();

            host.Run();
        }

        private static SecureString ReadSecureKey(string key)
        {
            var secureKey = new SecureString();
            for (int i = 0; i < key.Length; i++)
            {
                secureKey.AppendChar(key[i]);
            }
            return secureKey;
        }
    }
}
