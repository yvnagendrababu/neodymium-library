package com.xceptance.neodymium.module.order;

import com.xceptance.neodymium.module.vector.BrowserVectorBuilder;
import com.xceptance.neodymium.module.vector.MethodVectorBuilder;
import com.xceptance.neodymium.module.vector.ParameterVectorBuilder;
import com.xceptance.neodymium.module.vector.TestdataVectorBuilder;

public class DefaultVectorRunOrder extends VectorRunOrder
{
    public DefaultVectorRunOrder()
    {
        vectorRunOrder.add(BrowserVectorBuilder.class);
        vectorRunOrder.add(TestdataVectorBuilder.class);
        vectorRunOrder.add(ParameterVectorBuilder.class);
        vectorRunOrder.add(MethodVectorBuilder.class);
        // vectorRunOrder.add(new TestdataVector());
        // vectorRunOrder.add(new ParameterVector());
    }
}
