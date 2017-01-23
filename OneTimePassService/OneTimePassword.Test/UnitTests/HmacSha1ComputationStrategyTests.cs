using System;
using System.Security;
using NUnit.Framework;

namespace OneTimePassword.Test.UnitTests
{
    [TestFixture]
    public class HmacSha1ComputationStrategyTests
    {
        private const string TestUserName = "User";
        private const long TestTimeCounter = 20;

        [Test]
        public void StrategyComputesDifferentHmacOfDifferentSampleData()
        {
            SecureString secureKey = new SecureString();
            secureKey.AppendString(new char[] { 'k', 'e', 'y' });
            var strategyUnderTest = new HmacSha1ComputationStrategy();
            var hash1 = strategyUnderTest.ComputeHmac(secureKey, TestUserName, TestTimeCounter);
            var hash2 = strategyUnderTest.ComputeHmac(secureKey, TestUserName, TestTimeCounter + 1);

            Assert.IsNotNull(hash1);
            Assert.AreEqual(20, hash1.Length);

            Assert.IsNotNull(hash2);
            Assert.AreEqual(20, hash2.Length);

            for (int i = 0; i < hash1.Length; i++)
            {
                Assert.AreNotEqual(hash1[i], hash2[i]);
            }
        }
    }
}