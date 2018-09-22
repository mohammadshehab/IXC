package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.HashMap;
import java.util.logging.Logger;

public class ActivateContractAction extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ActivateContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/2, 2009/11/06";
  protected RegistryNode a;
  int b;
  long c;

  protected void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    _log.finest("WAB: getting parameter for command CONTRACT.WRITE");
    SVLObject localSVLObject = K.createSVLObject();
    Long localLong = (Long)b.a(Long.class, paramHashMap, "CO_ID", false);
    if (localLong != null)
      localSVLObject.setValue("CO_ID", localLong);
    else
      localSVLObject.setValue("CO_ID_PUB", (String)b.a(String.class, paramHashMap, "CO_ID_PUB", true));
    localSVLObject.setValue("CO_STATUS", this.b);
    localSVLObject.setValue("REASON", this.c);
    _log.finest("WAB: Execute command CONTRACT.WRITE");
    paramServiceAccessor.execute("CONTRACT.WRITE", localSVLObject);
    _log.finest("WAB: Command CONTRACT.WRITE executed");
    _log.finest("WAB: Commit all commands");
    paramServiceAccessor.commit();
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.a == null)
      this.a = RegistryHelper.getNode(str);
    return this.a;
  }

  protected void a()
  {
    this.a = c();
    this.b = RegistryHelper.getIntEntryValue(this.a, "Status", false, 2);
    this.c = RegistryHelper.getIntEntryValue(this.a, "StatusReason", false, 1);
  }

  protected HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("CO_ID", null);
    localHashMap.put("CO_ID_PUB", null);
    localHashMap.put("ORDER_DEALER_ID", null);
    return localHashMap;
  }

  public void end()
  {
    this.a = null;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ActivateContractAction
 * JD-Core Version:    0.6.0
 */