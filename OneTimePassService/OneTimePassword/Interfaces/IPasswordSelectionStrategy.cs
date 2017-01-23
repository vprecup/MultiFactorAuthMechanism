namespace OneTimePassword
{
    public interface IPasswordSelectionStrategy
    {
        string GetPassword(byte[] hmacCode);
    }
}