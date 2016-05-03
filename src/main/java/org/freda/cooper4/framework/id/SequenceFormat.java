package org.freda.cooper4.framework.id;

import org.freda.cooper4.framework.id.exception.SequenceFormatException;

/**
 * Created by rally on 16/4/30.
 */
public interface SequenceFormat
{
    public String format(long pSequence) throws SequenceFormatException;
}
