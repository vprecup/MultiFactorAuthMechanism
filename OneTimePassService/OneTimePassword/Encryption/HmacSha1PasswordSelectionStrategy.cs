
using System;
using System.Diagnostics;

namespace OneTimePassword
{
    public class HmacSha1PasswordSelectionStrategy : IPasswordSelectionStrategy
    {
        private uint passwordLength;

        public HmacSha1PasswordSelectionStrategy(uint passwordLength)
        {
            this.ValidatePasswordLength(passwordLength);
            this.passwordLength = passwordLength;
        }

        public string GetPassword(byte[] hmacCode)
        {
            Debug.Assert(hmacCode.Length == 20, "The hmacCode should contain exactly 20 bytes.");

            // 1. Select start index to be the value specified by the last significant 4 bits. 
            int startIndex = hmacCode[hmacCode.Length - 1] & 0x0F;

            // 2. Get the uint representation of the 4 bytes starting at the position specified by the startIndex.
            uint passValue = BitConverter.ToUInt32(hmacCode, startIndex);

            // 3. Based on the password length, get the last x digits of the obtained uint.
            
            // REMARK: due to the lack of support for marshaing a secure string (i.e. no Marshal.SecureStringToXxx) support in .NET Core yet,
            // I decided to leave this in clear text for demoability purpose. The alternative would be to "export" everything into a
            // secure string and compute the objects' hash values on the server side. 
            var passValueString = passValue.ToString();
            if (passValueString.Length < (int)this.passwordLength)
            {
                return passValueString.PadLeft((int)this.passwordLength, '0');
            }

            return passValueString.Substring(passValueString.Length - (int)this.passwordLength);
        }

        private void ValidatePasswordLength(uint passwordLength)
        {
            Debug.Assert(passwordLength >= 4, "Password length would be too weak: " + passwordLength);
            Debug.Assert(passwordLength < 10, "Potential usability issue. Password length too long: " + passwordLength);
        }
    }
}
