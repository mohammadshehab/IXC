package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.HashMap;
import java.util.logging.Logger;

public class CheckContractMarketDevice extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/CheckContractMarketDevice.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/2, 2009/11/06";
  private static final char b = 'P';
  private static final char c = 'S';
  protected RegistryNode a;

  protected void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    Long localLong = (Long)b.a(Long.class, paramHashMap, "RESOURCE_ID");
    if (localLong == null)
    {
      long l1 = ((Long)b.a(Long.class, paramHashMap, "SCCODE", true)).longValue();
      _log.finest("WAB: Execute command MARKETS.READ");
      SVLObject localSVLObject = paramServiceAccessor.execute("MARKETS.READ", null);
      _log.finest("WAB: Command executed");
      SVLObjectList localSVLObjectList = localSVLObject.getSVLObjectList("markets");
      for (int i = 0; i < localSVLObjectList.size(); i++)
      {
        long l2 = localSVLObjectList.get(i).getLongValue("SCCODE");
        if (l2 != l1)
          continue;
        int j = localSVLObjectList.get(i).getCharValue("MRKT_TYPE");
        if ((j != 80) && (j != 83))
          continue;
        HashMap localHashMap = new HashMap();
        localHashMap.put("DEVICE_REQUIRED", Boolean.TRUE);
        paramHashMap.put("CONTEXT_VARIABLES_FOR_UPDATE", localHashMap);
        break;
      }
    }
  }

  protected void a()
  {
    if (this.a == null)
      c();
  }

  protected HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("SCCODE", null);
    localHashMap.put("RESOURCE_ID", null);
    return localHashMap;
  }

  public void end()
  {
    this.a = null;
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.a == null)
      this.a = RegistryHelper.getNode(str);
    return this.a;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.CheckContractMarketDevice
 * JD-Core Version:    0.6.0
 */