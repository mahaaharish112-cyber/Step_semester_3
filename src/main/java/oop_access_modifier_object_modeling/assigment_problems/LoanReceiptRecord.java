import java.util.Arrays;

public class LoanReceiptRecord {

    private final String memberId;
    private final String[] bookIds;

    static {
        System.setProperty("loan.receipt.ready", "true");
    }

    public LoanReceiptRecord(String memberId, String[] bookIds) {

        if (bookIds == null || bookIds.length > 20) {
            throw new IllegalArgumentException("Invalid book IDs");
        }

        for (String id : bookIds) {
            if (id == null || !id.matches("BK-\\d{3}")) {
                throw new IllegalArgumentException("Invalid book ID");
            }
        }

        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceiptRecord withCorrectedBookId(int index, String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException();
        }

        if (newId == null || !newId.matches("BK-\\d{3}")) {
            throw new IllegalArgumentException("Invalid book ID");
        }

        String[] correctedBookIds = Arrays.copyOf(bookIds, bookIds.length);
        correctedBookIds[index] = newId;

        return new LoanReceiptRecord(memberId, correctedBookIds);
    }
}
