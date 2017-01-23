package vigenere;

public class encryption extends VigenereCipher{

    public String encrypt(String text, String key)
    {
        String encodedSentence = "";
        char characters[] = new char[text.length()];
        for(int i=0, j=0; i<text.length(); i++)
        {
                characters[i] = text.charAt(i);
                if(characters[i] == ' ')
                {
                    encodedSentence = encodedSentence + ' ';
                    continue;
                }
                    if(characters[i]>=97 && characters[i]<=122)
                    {
                            key = key.toLowerCase();
                            characters[i] = (char) (((characters[i]+key.charAt(j)-(2*97))%26)+97);//modulus wraps around a particular number, much like a loop
                            encodedSentence = encodedSentence + characters[i];
                            j++;
                            j = j%key.length();
                    }
                    
                    else if(characters[i]>=65 && characters[i]<=90)
                    {
                            key = key.toUpperCase();
                            characters[i] = (char) (((characters[i]+key.charAt(j)-(2*65))%26)+65);
                            encodedSentence = encodedSentence + characters[i];
                            j++;
                            j = j%key.length();
                    }
                    else
                        encodedSentence = encodedSentence + characters[i];
                    
            
        }
        
        return encodedSentence;
    }
    
    public String decrypt(String text, String key)
    {
        String decodedSentence = "";
        char characters[] = new char[text.length()];
        for(int i=0, j=0; i<text.length(); i++)
        {
                characters[i] = text.charAt(i);
                if(characters[i] == ' ')
                {
                    decodedSentence = decodedSentence + ' ';
                    continue;
                }
                    if(characters[i]>=97 && characters[i]<=122)
                    {
                            key = key.toLowerCase();
                            characters[i] = (char) (((characters[i]-key.charAt(j)+26)%26)+97);//modulus wraps around a particular number, much like a loop
                            decodedSentence = decodedSentence + characters[i];
                            j++;
                            j = j%key.length();
                    }
                    
                    else if(characters[i]>=65 && characters[i]<=90)
                    {
                            key = key.toUpperCase();
                            characters[i] = (char) (((characters[i]-key.charAt(j)+26)%26)+65);
                            decodedSentence = decodedSentence + characters[i];
                            j++;
                            j = j%key.length();
                    }
                    else
                        decodedSentence = decodedSentence + characters[i];
                    
            
        }
        
        return decodedSentence;
    }

}
