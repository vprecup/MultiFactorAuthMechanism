using System;
using System.Security;

namespace OneTimePassword.Runner
{
    public class Program
    {
        private static Client client;
        private static Server server;
        
        public static void Main(string[] args)
        {
            Console.WriteLine("Welcome to the VeInteractive challenge! Please enter the shared key for the client and server and then press Enter...");
            var secureKey = new SecureString();

            // Get the shared key.
            ConsoleKeyInfo key = Console.ReadKey(true);
            while (key.Key != ConsoleKey.Enter)
            {
                Console.Write('*');
                secureKey.AppendChar(key.KeyChar);
                key = Console.ReadKey(true);
            }

            
            // Build the Client and Server.
            var config = new OneTimePasswordGenerationConfiguration(new DateTime(2000,1,1), TimeSpan.FromSeconds(30), 6);
            var factory = new OneTimePasswordGeneratorFactory(config);
            client = new Client(secureKey, factory);
            server = new Server(secureKey, factory);

            // Display the options and accept user input. 
            ProgramLoop();
        }

        private static void ProgramLoop()
        {
            while (true)
            {
                Console.WriteLine();
                Console.WriteLine("You now have 2 options: ");
                Console.WriteLine("[g] Generate a One Time Password for a certain user.");
                Console.WriteLine("[v] Check if a certain One Time Password for a certain user is valid.");
                Console.WriteLine("[Esc] Exit the program");

                var key = Console.ReadKey(true);
                switch (key.Key)
                {
                    case ConsoleKey.G:
                        GeneratePass();
                        break;
                    case ConsoleKey.V:
                        ValidatePass();
                        break;
                    case ConsoleKey.Escape:
                        return;
                }
            }
        }

        private static void GeneratePass()
        {
            Console.WriteLine("---Password generation wizard---");
            Console.Write("User Id: ");
            var userId = Console.ReadLine();

            Console.WriteLine("The current time is: " + DateTime.UtcNow.ToString());
            Console.WriteLine("-------------");

            var password = client.GeneratePassword(userId);
            Console.WriteLine("The generated password is: " + password);
        }

        private static void ValidatePass()
        {
            Console.WriteLine("---Password validation wizard---");
            
            Console.Write("User Id: ");
            var userId = Console.ReadLine();

            Console.Write("Password to validate: ");
            var password = Console.ReadLine();

            Console.WriteLine("The current time is: " + DateTime.UtcNow.ToString());
            Console.WriteLine("-------------");

            if (server.ValidatePassword(password, userId))
            {
                Console.WriteLine("Validation SUCCEEDED!");
                return;
            }

            Console.WriteLine("Validation FAILED!!! Be faster next time! :(");
        }
    }
}
