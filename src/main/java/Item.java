public class Item implements Comparable<Item> {
    private int startMinutes;
    private int startHour;
    private int endMinutes;
    private int endHour;
    private CompanyName companyName;


    public Item(CompanyName companyName, int startHour, int startMinutes, int endHour, int endMinutes) {
        this.startMinutes = startMinutes;
        this.startHour = startHour;
        this.endMinutes = endMinutes;
        this.endHour = endHour;
        this.companyName = companyName;
    }

    public int getStartMinutes() {
        return startMinutes;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndMinutes() {
        return endMinutes;
    }

    public int getEndHour() {
        return endHour;
    }

    public CompanyName getCompanyName() {
        return companyName;
    }

    public int getTimeStart() {
        return startHour * 60 + startMinutes;
    }

    public int getTimeEnd() {
        return endHour * 60 + endMinutes;
    }

    @Override
    public int compareTo(Item o) {

        int timeEnd = this.endHour * 60 + this.endMinutes;
        int timeEndO = o.endHour * 60 + o.endMinutes;
        int timeStart = this.startHour * 60 + this.startMinutes;
        int timeStartO = o.startHour *60 + o.startMinutes;

        if (getTimeEnd() == o.getTimeEnd()) {
            if (getTimeStart() == o.getTimeStart()) {
                return (companyName.equals(CompanyName.POSH)) ? -1 : 1;
            } else {
                return o.getTimeStart() - getTimeStart();
            }
        } else {
            return timeEnd - timeEndO;
        }
    }
}
