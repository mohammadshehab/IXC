package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

public class ConvertBonusPointAction extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/ConvertBonusPointAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/3, 2009/11/06";
  protected RegistryNode a;
  protected String b;
  protected String c;

  public void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    SVLObject localSVLObject1 = K.createSVLObject();
    long l1 = ((Long)b.a(Long.class, paramHashMap, "CS_ID", true)).longValue();
    localSVLObject1.setValue("CS_ID", l1);
    long l2 = ((Long)b.a(Long.class, paramHashMap, "PP_ID", true)).longValue();
    localSVLObject1.setValue("PP_ID", l2);
    b.a(localSVLObject1, "APPLY_ALL_REWARDS", Boolean.class, paramHashMap, "APPLY_ALL_REWARDS", false);
    HashMap localHashMap1 = (HashMap)b.a(HashMap.class, paramHashMap, "BP_CHANGE", false);
    HashMap localHashMap2 = (HashMap)b.a(HashMap.class, paramHashMap, "BP_AFFECTED", false);
    SVLObjectList localSVLObjectList1 = K.createSVLObjectList();
    SVLObjectList localSVLObjectList2 = K.createSVLObjectList();
    if ((localHashMap1 != null) && (localHashMap2 != null))
    {
      HashMap localHashMap3 = localHashMap1;
      HashMap localHashMap4 = localHashMap2;
      Iterator localIterator1 = localHashMap3.values().iterator();
      while (localIterator1.hasNext())
      {
        SVLObject localSVLObject2 = K.createSVLObject();
        HashMap localHashMap5 = (HashMap)localIterator1.next();
        String str = (String)b.a(String.class, localHashMap5, "CHANGE_TYPE", true);
        Long localLong1 = (Long)b.a(Long.class, localHashMap5, "OLD_ID");
        Long localLong2 = (Long)b.a(Long.class, localHashMap5, "NEW_ID", false);
        Long localLong3 = (Long)b.a(Long.class, localHashMap5, "INDEX", true);
        SVLObjectList localSVLObjectList3 = K.createSVLObjectList();
        HashMap localHashMap6 = null;
        Iterator localIterator2 = localHashMap4.values().iterator();
        while (localIterator2.hasNext())
        {
          SVLObject localSVLObject3 = K.createSVLObject();
          localHashMap6 = (HashMap)localIterator2.next();
          Long localLong4 = (Long)b.a(Long.class, localHashMap6, "INDEX", true);
          if (localLong3.equals(localLong4))
          {
            Long localLong5 = (Long)b.a(Long.class, localHashMap6, "CO_ID");
            if (localLong5 != null)
              localSVLObject3.setValue("CO_ID", localLong5);
            else
              localSVLObject3.setValue("CO_ID_PUB", (String)b.a(String.class, localHashMap6, "CO_ID_PUB", true));
            if (str.equals(this.c))
            {
              Long localLong6 = (Long)b.a(Long.class, localHashMap6, "SNCODE");
              localSVLObject3.setValue("SNCODE", localLong6);
              Long localLong7 = (Long)b.a(Long.class, localHashMap6, "PROFILE_ID");
              localSVLObject3.setValue("PROFILE_ID", localLong7);
            }
            localSVLObjectList3.add(localSVLObject3);
          }
        }
        if (str.equals(this.c))
        {
          localSVLObject2.setValue("NEW_FUP_ID", localLong2);
          localSVLObject2.setValue("OLD_FUP_ID", localLong1);
          localSVLObject2.setValue("FREE_UNIT_SERVICE_LIST", localSVLObjectList3);
          localSVLObjectList1.add(localSVLObject2);
        }
        else
        {
          localSVLObject2.setValue("NEW_RPCODE", localLong2);
          localSVLObject2.setValue("OLD_RPCODE", localLong1);
          localSVLObject2.setValue("CONTRACT_LIST", localSVLObjectList3);
          localSVLObjectList2.add(localSVLObject2);
        }
      }
      localSVLObject1.setValue("FUP_CHANGE_LIST", localSVLObjectList1);
      localSVLObject1.setValue("RATEPLAN_CHANGE_LIST", localSVLObjectList2);
    }
    _log.finest("WAB: trying to execute BONUSPOINT_CONVERSION.EXECUTE command.");
    paramServiceAccessor.execute("BONUSPOINT_CONVERSION.EXECUTE", localSVLObject1);
    _log.finest("WAB: BONUSPOINT_CONVERSION.EXECUTE command executed.");
    _log.finest("WAB: Commit all commands");
    paramServiceAccessor.commit();
  }

  public HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("CS_ID", null);
    localHashMap.put("PP_ID", null);
    localHashMap.put("APPLY_ALL_REWARDS", null);
    localHashMap.put("BP_CHANGE", null);
    localHashMap.put("BP_AFFECTED", null);
    localHashMap.put("ORDER_DEALER_ID", null);
    return localHashMap;
  }

  public void end()
  {
    this.a = null;
    this.b = "";
    this.c = "";
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
    this.b = RegistryHelper.getStringEntryValue(this.a, "RpListCode", true, null);
    this.c = RegistryHelper.getStringEntryValue(this.a, "FupListCode", true, null);
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.ConvertBonusPointAction
 * JD-Core Version:    0.6.0
 */