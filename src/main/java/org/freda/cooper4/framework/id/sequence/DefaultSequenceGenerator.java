package org.freda.cooper4.framework.id.sequence;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.id.SequenceGenerator;
import org.freda.cooper4.framework.id.SequenceStore;
import org.freda.cooper4.framework.id.exception.SequenceCreateException;

/**
 *
 * DefaultSequenceGenerator
 * 顺序发生器
 *
 * Created by rally on 16/4/29.
 */
public class DefaultSequenceGenerator implements SequenceGenerator
{
    private static final Log log = LogFactory.getLog(DefaultSequenceGenerator.class);

    /**
     * 序号最小值
     */
    protected long minValue = 0L;
    /**
     * 序号最大值
     */
    protected long maxValue = Long.MAX_VALUE;
    /**
     * cache大小，用于确定预分配序号数;cache越大，ＩＤ生成效率越高，但是当系统 关闭时，可能造成的ＩＤ浪费也会更多.
     */
    protected int cache = 100;
    /**
     * 是否循环生成，当cycle达到最大值时，是否循环，又从最小值开始生成
     */
    protected boolean cycle = false;
    /**
     * 当前实际已分配序号最大值
     */
    protected long currCount = 0L;

    private boolean initiated = false;

    private String id;
    /**
     * ID存取.
     */
    private SequenceStore sequenceStore;

    public DefaultSequenceGenerator()
    {}

    /**
     * 初始化.
     */
    private void init(String id)
    {
        initiated = true;

        this.id = id;

        long initValue = sequenceStore == null ? 0L : sequenceStore.load(id);

        initValue = Math.max(initValue,minValue);

        if (initValue > maxValue)
        {
            if (cycle)
            {
                initValue = minValue;
            }
            else
            {
                String msg = id + "序列号生成器已经达到最大值 :" + maxValue;

                log.error(msg);

                throw new SequenceCreateException(msg);
            }
        }
        currCount = initValue;
        //currCount ++;
    }

    /**
     * 获取下一个
     * @return next Sequence
     */
    @Override
    public synchronized long next(String id) throws SequenceCreateException
    {
        if (!initiated || this.id == null || !id.equals(this.id))
            init(id);//初始化.

        if (currCount >= maxValue)
        {
            if (cycle)
            {
                currCount = minValue;
            }
            else
            {
                String msg = id + "序列号生成器已经达到最大值 :" + maxValue;

                log.error(msg);

                throw new SequenceCreateException(msg);
            }
        }
        currCount++;

        sequenceStore.update(currCount,id);

        return currCount;
    }

    public DefaultSequenceGenerator setSequenceStore(SequenceStore sequenceStore)
    {
        this.sequenceStore = sequenceStore;

        return this;
    }
}
