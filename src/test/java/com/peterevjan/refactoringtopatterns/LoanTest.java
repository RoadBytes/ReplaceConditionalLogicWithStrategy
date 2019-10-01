package com.peterevjan.refactoringtopatterns;

import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;

public class LoanTest {
    @Test
    public void testTermLoanNoPayments() {
        double commitment = 3.1;
        int riskRating = 3;
        Date maturity = new Date();
        Loan termLoan = new Loan(commitment, riskRating, maturity);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, 0.0);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, null);
        assertEquals(termLoan.capitalStrategy.getClass().getSimpleName(), "CapitalStrategyTermLoan");
    }

    @Test
    public void testTermLoanOnePayment() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = new Date();
        Loan termLoan = new Loan(commitment, riskRating, maturity);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, 0.0);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, null);
        assertEquals(termLoan.capitalStrategy.getClass().getSimpleName(), "CapitalStrategyTermLoan");
    }

    @Test
    public void testTermLoanWithRiskAdjustedCapitalStrategy() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = new Date();
        CapitalStrategy riskAdjustedCapitalStrategy = new CapitalStrategyTermLoan();
        double outstanding = 220.0;
        Loan termLoan = new Loan(riskAdjustedCapitalStrategy, commitment, outstanding, riskRating, maturity, null);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, outstanding);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, null);
        assertEquals(termLoan.capitalStrategy, riskAdjustedCapitalStrategy);
    }

    @Test
    public void testRevolver() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = null;
        double outstanding = 220.0;
        Date expiry = new Date();
        Loan termLoan = new Loan(commitment, outstanding, riskRating, maturity, expiry);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, outstanding);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, expiry);
        assertEquals(termLoan.capitalStrategy.getClass().getSimpleName(), "CapitalStrategyRevolver");
    }

    @Test
    public void testRevolverWithRevolverCapitalStrategy() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = null;
        double outstanding = 220.0;
        Date expiry = new Date();
        CapitalStrategy capitalStrategy = new CapitalStrategyRevolver();

        Loan termLoan = new Loan(capitalStrategy, commitment, outstanding, riskRating, maturity, expiry);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, outstanding);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, expiry);
        assertEquals(termLoan.capitalStrategy, capitalStrategy);
    }

    @Test
    public void testRCTL() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = new Date();
        double outstanding = 220.0;
        Date expiry = new Date();

        Loan termLoan = new Loan(commitment, outstanding, riskRating, maturity, expiry);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, outstanding);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, expiry);
        assertEquals(termLoan.capitalStrategy.getClass().getSimpleName(), "CapitalStrategyRCTL");
    }

    @Test
    public void testRCTLWithCapitalStrategy() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = new Date();
        Date expiry = new Date();
        CapitalStrategy capitalStrategy = new CapitalStrategyRCTL();

        Loan termLoan = new Loan(capitalStrategy, commitment, riskRating, maturity, expiry);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, 0.0);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, expiry);
        assertEquals(termLoan.capitalStrategy, capitalStrategy);
    }

    @Test
    public void testRCTLLoanWithExpiry() {
        double commitment = 3.1;
        int riskRating = 1;
        Date maturity = new Date();
        Date expiry = new Date();
        Loan termLoan = new Loan(commitment, riskRating, maturity, expiry);

        assertEquals(termLoan.commitment, commitment);
        assertEquals(termLoan.outstanding, 0.0);
        assertEquals(termLoan.riskRating, riskRating);
        assertEquals(termLoan.maturity, maturity);
        assertEquals(termLoan.expiry, expiry);
        assertEquals(termLoan.capitalStrategy.getClass().getSimpleName(), "CapitalStrategyRCTL");
    }
}