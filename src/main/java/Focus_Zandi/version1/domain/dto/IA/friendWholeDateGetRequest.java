package Focus_Zandi.version1.domain.dto.IA;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class friendWholeDateGetRequest {
    private String username;

    private String friendUserName;

    private Timestamp date;
}
