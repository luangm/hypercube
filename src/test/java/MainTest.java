import io.luan.hypercube.entity.*;
import org.junit.Test;

/**
 * Created by Miao on 5/20/2016.
 */
public class MainTest {

    @Test
    public void test1() {

        Concept assetConcept = new Concept("Asset");
        Concept liabConcept = new Concept("Liability");
        Concept equityConcept = new Concept("Equity");
        Concept endEquityConcept = new Concept("EndEquity");
        Concept piConcept = new Concept("PI");


        Context context = new Context();

        Period period = new Period(2016, 1, 1);
        Period period2 = new Period(2016, 12, 31);

        Fact asset = new Fact();
        asset.setConcept(assetConcept);
        asset.setContext(context);
        asset.setPeriod(period);
        asset.setQuantity(new Quantity(100, "USD"));

        Fact liability = new Fact();
        liability.setConcept(liabConcept);
        liability.setContext(context);
        liability.setPeriod(period);
        liability.setQuantity(new Quantity(200, "USD"));

        ComputedFact equity = new ComputedFact();
        equity.setConcept(equityConcept);
        equity.setContext(context);
        equity.setPeriod(period);
        equity.setFormula("Asset + Liability");

        Fact asset2 = new Fact();
        asset2.setConcept(assetConcept);
        asset2.setContext(context);
        asset2.setPeriod(period2);
        asset2.setQuantity(new Quantity(500, "USD"));

        Fact liability2 = new Fact();
        liability2.setConcept(liabConcept);
        liability2.setContext(context);
        liability2.setPeriod(period2);
        liability2.setQuantity(new Quantity(600, "USD"));

        ComputedFact equity2 = new ComputedFact();
        equity2.setConcept(equityConcept);
        equity2.setContext(context);
        equity2.setPeriod(period2);
        equity2.setFormula("Asset + Liability");

        ComputedFact equity3 = new ComputedFact();
        equity3.setConcept(endEquityConcept);
        equity3.setContext(context);
        equity3.setPeriod(period2);
        equity3.setFormula("Equity * #PI");


        Constant pi = new Constant();
        pi.setConcept(piConcept);
        pi.setContext(context);
        pi.setQuantity(new Quantity(3.14, ""));

        Hypercube cube = new Hypercube();

        cube.addConstant(pi);

        cube.addFact(asset);
        cube.addFact(liability);
        cube.addFact(equity);

        cube.addFact(asset2);
        cube.addFact(liability2);
        cube.addFact(equity2);
        cube.addFact(equity3);


        System.out.println(equity.getQuantity());
        System.out.println(equity2.getQuantity());

        System.out.println(equity3.getQuantity());

    }
}
