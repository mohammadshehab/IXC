package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.func.reg.RegistryNode;

public class DeactivateContractAction extends AbstractChangeStatusContractAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/DeactivateContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/2, 2009/11/06";
  RegistryNode c;

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.c == null)
      this.c = RegistryHelper.getNode(str);
    return this.c;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.DeactivateContractAction
 * JD-Core Version:    0.6.0
 */