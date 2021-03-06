import org.zkoss.ztl.JQuery;


public class SS_028_05_Test extends SSAbstractTestCase {

    @Override
    protected void executeTest() {
    	//freeze some rows first
    	click(jq("$viewMenu"));
    	waitResponse();
    	mouseOver(jq("$freezeRows"));
    	waitResponse();
    	click(jq("$freezeRow5"));
    	waitResponse();
    	// TODO: Verify correct row is frozen
    	verifyTrue(jq("div.zstopblock").width() != 0);
    	JQuery p = jq("div.zstopblock");
    	int clen = p.children().length();
    	JQuery c = p.children("div:nth-child(5)");
    	int width = c.width();
    	verifyTrue("no. of children=" + clen + ", widht=" + width, clen == 5 && jq("div.zstopblock").children("div:nth-child(5)").width() != 0);
    }
}
