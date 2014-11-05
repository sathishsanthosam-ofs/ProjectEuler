import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex
{

    /**
     * @param args
     */
    public static void main(final String[] args)
    {
        String text = "\"more, more text\",\"ha,ha,ha\",\"ha,ha,ha\",\"ha,ha,ha\",\"ha,ha,ha\"";
        final String quotedToken = "(?x)" + "\"" + "(" + "  \\\\." + "  |" + "  [^\\\\\r\n\"]" + ")" + "*" + "\"";
        text = text.replaceAll(quotedToken, "\"\"");
        // String text = "start. \"in quotes!\"; foo? \"more \\\" words\"; bar";
        final String simpleToken = "\\Q,\\E";
        // String simpleToken = "[^.;?!\\s\"]+";

        final Pattern pattern = Pattern.compile(simpleToken);
        final Matcher matcher = pattern.matcher(text);
        int start = 0;
        while (matcher.find())
        {
            System.out.println(text.substring(start, matcher.start()));
            start = matcher.end();
        }


    }
}
