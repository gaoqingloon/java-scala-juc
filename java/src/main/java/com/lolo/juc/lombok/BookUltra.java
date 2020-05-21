package com.lolo.juc.lombok;

import lombok.*;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Accessors(chain = true)
@ToString
public class BookUltra {

    private @Getter Integer id;
    private @Setter@Getter String bookName;
    private @Getter double price;
    private @NonNull String author;
}
