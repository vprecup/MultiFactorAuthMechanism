using System;
using NUnit.Framework;

namespace OneTimePassword.Test.UnitTests
{
    [TestFixture]
    public class TimeCounterProviderTests
    {
        private static DateTime TestEpoch = new DateTime(1900, 1, 1, 0, 0, 0);
        private static TimeSpan TestInterval = TimeSpan.FromSeconds(30);

        [Test]
        public void ProviderThrowsWhenInvalidTime()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.Throws<ArgumentException>(() => providerUnderTest.GetTimeCounterUntil(TestEpoch - TimeSpan.FromSeconds(1)));
        }

        [Test]
        public void ProviderReturnsT0WhenParameterIsSameAsEpoch()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.AreEqual(0, providerUnderTest.GetTimeCounterUntil(TestEpoch));
        }

        [Test]
        public void ProviderReturnsT0WhenParameterIsBelowEpochPlusInterval()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.AreEqual(0, providerUnderTest.GetTimeCounterUntil(TestEpoch + TimeSpan.FromMilliseconds(29999)));
        }

        [Test]
        public void ProviderReturnsCorrectValueWhenParameterIsPositiveSmallValue()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.AreEqual(2*2 + 1, providerUnderTest.GetTimeCounterUntil(TestEpoch + TimeSpan.FromMinutes(2)));
        }

        [Test]
        public void ProviderReturnsCorrectValueWhenParameterIsPositiveSmallValue2()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.AreEqual(2*2 + 1, providerUnderTest.GetTimeCounterUntil(TestEpoch + TimeSpan.FromSeconds(123)));
        }

        [Test]
        public void ProviderReturnsCorrectValueWhenParameterIsPositiveLargeValue()
        {
            var providerUnderTest = new TimeCounterProvider(TestEpoch, TestInterval);
            Assert.AreEqual(2*24*60*2 + 1, providerUnderTest.GetTimeCounterUntil(TestEpoch + TimeSpan.FromDays(2)));
        }

        public static void Main(string[] args) { }
    }
}