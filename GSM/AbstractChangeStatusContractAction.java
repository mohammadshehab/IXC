package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.ect.ComponentException;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.action.AbstractContractAction;
import java.util.HashMap;
import java.util.logging.Logger;

public abstract class AbstractChangeStatusContractAction extends AbstractContractAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/AbstractChangeStatusContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/3, 2009/11/06";
  protected int a;
  protected long b;

  protected HashMap d()
  {
    return new HashMap();
  }

  protected void a(SVLObject paramSVLObject, HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    Long localLong = null;
    if (paramSVLObject != null)
      localLong = Long.valueOf(paramSVLObject.getLongValue("CO_ID"));
    if (localLong == null)
    {
      _log.severe("WAB: returned SVLObject returned by the search command didn't has CO_ID ");
      throw new ComponentException("WAB.ParameterNotFound", new Object[] { "CO_ID" });
    }
    _log.finest("WAB: try to execute CONTRACT.WRITE");
    SVLObject localSVLObject = K.createSVLObject();
    localSVLObject.setValue("CO_ID", localLong);
    localSVLObject.setValue("CO_STATUS", this.a);
    localSVLObject.setValue("REASON", this.b);
    paramServiceAccessor.execute("CONTRACT.WRITE", localSVLObject);
    _log.finest("WAB: CONTRACT.WRITE executed");
    a("contract", paramServiceAccessor);
  }

  public void end()
  {
    this.E = null;
    this.C = null;
    this.D = null;
    this.a = 0;
    this.l = null;
    this.b = 0L;
    this.m = 0L;
    this.n = 0;
    this.o = 0;
    this.p = 0;
  }

  protected void e()
  {
    RegistryNode localRegistryNode = c();
    _log.finest("WAB: try to set reasonKey attribute");
    this.b = RegistryHelper.getIntEntryValue(localRegistryNode, "ReasonKey", true, -1);
    _log.finest("WAB: reasonKey attribute set");
    _log.finest("WAB: try to set contractStatusTo attribute");
    this.a = RegistryHelper.getIntEntryValue(localRegistryNode, "ContractStatusTo", true, -1);
    _log.finest("WAB: contractStatusTo attribute set");
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.AbstractChangeStatusContractAction
 * JD-Core Version:    0.6.0
 */