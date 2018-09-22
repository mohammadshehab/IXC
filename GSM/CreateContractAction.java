package com.lhs.wab.action.contract.bscs;

import com.lhs.adaptor.common.RegistryHelper;
import com.lhs.ccb.common.soi.ExchangeFormatFactory;
import com.lhs.ccb.common.soi.SVLObject;
import com.lhs.ccb.common.soi.SVLObjectList;
import com.lhs.ccb.common.soi.ServiceAccessor;
import com.lhs.ccb.func.reg.RegistryNode;
import com.lhs.wab.a.b;
import com.lhs.wab.action.AbstractCilAction;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateContractAction extends AbstractCilAction
{
  public static final String WHAT_ID = "@(#)lhsj_main/bscs/cms/src/wab/java/com/lhs/wab/action/contract/bscs/CreateContractAction.java, , IX_COLLECTIONS_R2_ESU01, IX_COLLECTIONS_R2_ESU01_110812, /main/9, 2010/04/26";
  protected String a;
  protected char b;
  protected long c;
  protected int d;
  protected int e;
  protected RegistryNode f;

  protected void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor)
  {
    SVLObject localSVLObject = a.a(paramHashMap, paramServiceAccessor, this.a);
    long l = localSVLObject.getLongValue("CO_ID");
    String str = localSVLObject.getStringValue("CO_ID_PUB");
    paramHashMap.put("CO_ID", Long.valueOf(l));
    Long localLong = (Long)b.a(Long.class, paramHashMap, "PREPAID_PROFILE_ID", false);
    if (null != localLong)
      a.a(paramHashMap, paramServiceAccessor, Long.valueOf(l));
    Boolean localBoolean = (Boolean)paramHashMap.get("DEVICE_REQUIRED");
    if ((null != localBoolean) && (localBoolean.booleanValue()))
      a.a(paramHashMap, paramServiceAccessor, l, this.e);
    a.a(paramHashMap, paramServiceAccessor, Long.valueOf(l), null);
    a.a(paramHashMap, paramServiceAccessor, Long.valueOf(l), null, this.d);
    a.a(paramHashMap, paramServiceAccessor, Long.valueOf(l), null, 'a');
    if (_log.isLoggable(Level.FINEST))
      _log.finest("WAB: Contract " + l + " created.");
    a(paramHashMap, paramServiceAccessor, l, str);
    HashMap localHashMap = new HashMap();
    localHashMap.put("CO_ID", Long.valueOf(l));
    paramHashMap.put("CONTEXT_VARIABLES_FOR_UPDATE", localHashMap);
    _log.finest("WAB: Commit all commands");
    paramServiceAccessor.commit();
  }

  private void a(HashMap paramHashMap, ServiceAccessor paramServiceAccessor, long paramLong, String paramString)
  {
    _log.finest("WAB: Getting parameter for command WORKFLOW_ENTITY.WRITE");
    SVLObject localSVLObject1 = K.createSVLObject();
    localSVLObject1.setValue("WF_ATTR_ID_PUB", "CO_ID");
    localSVLObject1.setValue("WF_VALUE_NUMBER", (float)paramLong);
    SVLObjectList localSVLObjectList1 = K.createSVLObjectList();
    localSVLObjectList1.add(localSVLObject1);
    localSVLObject1 = K.createSVLObject();
    localSVLObject1.setValue("WF_ATTR_ID_PUB", "CO_ID_PUB");
    localSVLObject1.setValue("WF_VALUE_VARCHAR", paramString);
    localSVLObjectList1.add(localSVLObject1);
    SVLObject localSVLObject2 = K.createSVLObject();
    localSVLObject2.setValue("WF_PART_NAME", "CONTRACT");
    localSVLObject2.setValue("ATTR_LIST", localSVLObjectList1);
    SVLObjectList localSVLObjectList2 = K.createSVLObjectList();
    localSVLObjectList2.add(localSVLObject2);
    Long localLong1 = (Long)b.a(Long.class, paramHashMap, "WORKFLOW_ENTITY_INTERNAL_KEY");
    SVLObject localSVLObject3 = K.createSVLObject();
    localSVLObject3.setValue("WF_ENTITY_ID", localLong1);
    Long localLong2 = (Long)b.a(Long.class, paramHashMap, "ACTIVITY_HIST_ID");
    localSVLObject3.setValue("ACTIVITY_HIST_ID", localLong2);
    localSVLObject3.setValue("PART_LIST", localSVLObjectList2);
    boolean bool = false;
    localSVLObject3.setValue("UPDATE_WORKFLOW", bool);
    _log.finest("WAB: Execute command WORKFLOW_ENTITY.WRITE");
    paramServiceAccessor.execute("WORKFLOW_ENTITY.WRITE", localSVLObject3);
    _log.finest("WAB: Command executed");
  }

  protected void a()
  {
    if (this.f == null)
      c();
    this.a = RegistryHelper.getStringEntryValue(this.f, "ContractType", true, null);
    this.c = RegistryHelper.getIntEntryValue(this.f, "TemplateID", true, -1);
    if (this.c == -1L)
      this.b = RegistryHelper.getStringEntryValue(this.f, "CosActionActive", true, "a").charAt(0);
    else
      this.b = RegistryHelper.getStringEntryValue(this.f, "CosActionInitialize", true, "i").charAt(0);
    this.d = RegistryHelper.getIntEntryValue(this.f, "Retention", true, -1);
    this.e = RegistryHelper.getIntEntryValue(this.f, "ResType", true, 1);
  }

  protected HashMap b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("CS_ID", null);
    localHashMap.put("CS_ID_PUB", null);
    localHashMap.put("PLCODE", null);
    localHashMap.put("PLCODE_PUB", null);
    localHashMap.put("SCCODE", null);
    localHashMap.put("SCCODE_PUB", null);
    localHashMap.put("RPCODE", null);
    localHashMap.put("RPCODE_PUB", null);
    localHashMap.put("SUBM_ID", null);
    localHashMap.put("SUBM_ID_PUB", null);
    localHashMap.put("SERVICE", null);
    localHashMap.put("HLCODE", null);
    localHashMap.put("HLCODE_PUB", null);
    localHashMap.put("NPCODE", null);
    localHashMap.put("NPCODE_PUB", null);
    localHashMap.put("RESOURCE", null);
    localHashMap.put("SERVICE_PARAM", null);
    localHashMap.put("ORDER_DEALER_ID", null);
    localHashMap.put("RESOURCE_ID", null);
    localHashMap.put("ALT_RPCODE", null);
    localHashMap.put("ALT_RPCODE_PUB", null);
    localHashMap.put("DESCRIPTION", null);
    localHashMap.put("CO_TRIAL", null);
    localHashMap.put("CO_TRIAL_END_DATE", null);
    localHashMap.put("CO_TOLLRATING", null);
    localHashMap.put("CO_SIGNED_DATE", null);
    localHashMap.put("CO_OP_DIR", null);
    localHashMap.put("CO_EQUIPMENT_STATUS", null);
    localHashMap.put("CO_ARCHIVE", null);
    localHashMap.put("TEMPLATE_ID", null);
    localHashMap.put("WORKFLOW_ENTITY_INTERNAL_KEY", null);
    localHashMap.put("DEVICE_REQUIRED", null);
    localHashMap.put("IVR_LANG_ID", null);
    localHashMap.put("PREPAID_PROFILE_ID", null);
    localHashMap.put("CO_PSTN_DIR", null);
    localHashMap.put("BM_ID", null);
    localHashMap.put("NOTIFICATION_MEDIUM_ID", null);
    localHashMap.put("CALL_DETAIL", null);
    localHashMap.put("CHARGING_PERIOD_ADJUSTMENT_REFERENCE", null);
    localHashMap.put("ACTIVITY_HIST_ID", null);
    return localHashMap;
  }

  public void end()
  {
    this.f = null;
    this.a = null;
  }

  protected RegistryNode c()
  {
    String str = "Action Adaptor Settings/Action Settings/" + this.G + "/" + "Internal Attributes";
    if (this.f == null)
      this.f = RegistryHelper.getNode(str);
    return this.f;
  }
}

/* Location:           C:\37011\java_server_exe_37011\lib\jar\cab.jar
 * Qualified Name:     com.lhs.wab.action.contract.bscs.CreateContractAction
 * JD-Core Version:    0.6.0
 */