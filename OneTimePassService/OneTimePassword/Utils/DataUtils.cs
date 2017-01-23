using System;
using System.Text;
using System.Security;

namespace OneTimePassword
{
    public static class DataUtils
    {
        public static byte[] ToByteArray(this int value)
        {
            return BitConverter.GetBytes(value);            
        }

        public static byte[] ToByteArray(this string value)
        {
            return Encoding.UTF8.GetBytes(value);            
        }

        public static void AppendString(this SecureString currentObject, char[] stringToAppend)
        {
            for (int i = 0; i < stringToAppend.Length; i++)
            {
                currentObject.AppendChar(stringToAppend[i]);
            }
        }
    }
}