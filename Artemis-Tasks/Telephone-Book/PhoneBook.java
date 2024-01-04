package fop.w5phone;
public class PhoneBook{
    private Entry[] entries;
    public PhoneBook(Entry[] entries){
        this.entries = entries;
    }
    public String find(String firstName, String lastName){
        int low = 0;
        int high = entries.length - 1;

        while(low <= high){
            int mid = low + (high - low) / 2;
            int lastNameComparison = entries[mid].getLastName().compareTo(lastName);

            if(lastNameComparison == 0){
                int firstNameComparison = entries[mid].getFirstName().compareTo(firstName);
                if(firstNameComparison == 0){
                    return "true";
                }else if(firstNameComparison < 0){
                    low = mid + 1;
                }else{
                    high = mid - 1;
                }
            }

            if(entries[mid].getFirstName().compareTo(firstName) < 0){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return null;
    }
}
