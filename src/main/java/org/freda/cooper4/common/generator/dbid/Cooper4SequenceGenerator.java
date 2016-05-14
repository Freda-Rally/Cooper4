package org.freda.cooper4.common.generator.dbid;

import org.freda.cooper4.framework.id.sequence.DefaultSequenceGenerator;
import org.freda.cooper4.framework.utils.SpringBeanLoader;
import org.springframework.stereotype.Component;

/**
 *
 *
 *
 * Created by rally on 16/4/30.
 */
@Component(value = "cooper4SequenceGenerator")
public class Cooper4SequenceGenerator extends DefaultSequenceGenerator
{
    /**
     * 实例.
     */
    private static Cooper4SequenceGenerator instance;

    /**
     * 单例实例化.
     * @return instance
     */
    public static Cooper4SequenceGenerator getInstance()
   {
       synchronized (Cooper4SequenceGenerator.class)
       {
            if (instance == null)
            {
                instance = SpringBeanLoader.getSpringBean("cooper4SequenceGenerator",Cooper4SequenceGenerator.class);
            }
       }
       return instance;
   }
}
