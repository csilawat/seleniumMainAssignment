package base;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    Logger logger = Logger.getLogger(Listener.class);

    @Override
    public void onTestStart(ITestResult result) {
        //System.out.println(result.getMethod().getMethodName()+"-----------onTestStart--------------");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //  System.out.println(result.getMethod().getMethodName()+"-----------onTestSuccess--------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //  System.out.println(result.getMethod().getMethodName()+"-----------onTestFailure--------------");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // System.out.println(result.getMethod().getMethodName()+"-----------onTestSkipped--------------");
    }

    @Override
    public void onFinish(ITestContext context) {

        logger.info("-Test Started on--: " + context.getStartDate() + "\n" + context.getPassedTests().getAllResults().size() + "---Total test \n" + context.getPassedTests().size() + "---Total passed \n" + context.getFailedTests().getAllResults().size() + "---Total Test failed-***\n" + context.getSkippedTests().getAllResults().size() + "--- Total Test skipped-***\n" + "-Test Ended on--: " + context.getEndDate());
        System.out.println("-Test Started on--: " + context.getStartDate());
        System.out.println(context.getPassedTests().getAllResults().size() + "---Total test");
        System.out.println(context.getPassedTests().size() + "---Total passed Tests");
        System.out.println(context.getFailedTests().getAllResults().size() + "---Total failed Tests-***");
        System.out.println(context.getSkippedTests().getAllResults().size() + "--- Total skipped Tests-***");

        System.out.println("-Test Ended on--: " + context.getEndDate());

    }
}
