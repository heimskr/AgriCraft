package com.infinityraider.agricraft.compat.computer.methods;

import com.infinityraider.agricraft.api.requirement.RequirementType;
import com.infinityraider.agricraft.api.plant.IAgriPlant;

public class MethodNeedsBaseBlock extends MethodBaseGrowthReq {
	
    public MethodNeedsBaseBlock() {
        super("needsBaseBlock");
    }

    @Override
    protected Object[] onMethodCalled(IAgriPlant plant) throws MethodException {
        return new Object[] {
            !plant.getGrowthRequirement().getRequiredType().orElse(RequirementType.NONE).equals(RequirementType.NONE)
        };
    }

}
