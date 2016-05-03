package org.freda.cooper4.framework.id.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.id.*;
import org.freda.cooper4.framework.id.exception.IDBaseException;
import org.freda.cooper4.framework.id.exception.SequenceCreateException;

/**
 *
 * 生成ID使用..
 *
 * Created by rally on 16/4/30.
 */
public class DefaultIDGenerator implements IDGenerator
{
    private static final Log log = LogFactory.getLog(DefaultIDGenerator.class);

    /**
     * 前缀
     */
    private PrefixGenerator prefixGenerator;
    /**
     * 序号
     */
    private SequenceGenerator sequenceGenerator;
    /**
     * 序号格式化.
     */
    private SequenceFormat sequenceFormat;


    public DefaultIDGenerator(SequenceGenerator sequenceGenerator)
    {
        this.sequenceGenerator = sequenceGenerator;
    }

    public DefaultIDGenerator(PrefixGenerator prefixGenerator,SequenceGenerator sequenceGenerator,
                              SequenceFormat sequenceFormat)
    {
        this.prefixGenerator = prefixGenerator;

        this.sequenceGenerator = sequenceGenerator;

        this.sequenceFormat = sequenceFormat;
    }

    @Override
    public String create(String id) throws IDBaseException
    {
        log.debug("开始生成.");

        final String prefix = prefixGenerator == null ? "" : prefixGenerator.create();

        log.debug("[前缀]:" + prefix);

        if(sequenceGenerator != null)
        {
            final long sequence = sequenceGenerator.next(id);

            log.debug("[序号]:" + sequence);

            final String formatSequence = sequenceFormat == null ? String.valueOf(sequence) : sequenceFormat.format(sequence);

            return prefix + formatSequence;
        }
        else
        {
            throw new SequenceCreateException("序号发生器为空.");
        }
    }


    public void setPrefixGenerator(PrefixGenerator prefixGenerator)
    {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSequenceGenerator(SequenceGenerator sequenceGenerator)
    {
        this.sequenceGenerator = sequenceGenerator;
    }

    public void setSequenceFormat(SequenceFormat sequenceFormat)
    {
        this.sequenceFormat = sequenceFormat;
    }
}
