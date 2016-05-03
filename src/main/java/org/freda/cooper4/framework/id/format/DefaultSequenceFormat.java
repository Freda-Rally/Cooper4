package org.freda.cooper4.framework.id.format;

import org.freda.cooper4.framework.id.SequenceFormat;
import org.freda.cooper4.framework.id.exception.SequenceFormatException;

import java.text.DecimalFormat;

/**
 *
 * DefaultSequenceFormater
 *
 * Created by rally on 16/4/29.
 */
public class DefaultSequenceFormat implements SequenceFormat
{
    private String pattern;

    public String format(long pSequence) throws SequenceFormatException
    {
        DecimalFormat df = new DecimalFormat(pattern);

        return df.format(pSequence);
    }


    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }
}
