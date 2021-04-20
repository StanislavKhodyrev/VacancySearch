package model.strategy;

/**
 * @author Stanislav Khodyrev
 */
public enum City {
    Moscow, StPetersburg, Yekaterinburg;

    /* Для успорения производительности при поиске, будем использовать коды городов для разных ресурсов */
    String getHHId() {
        return switch (this) {
            case Moscow -> "1";
            case StPetersburg -> "2";
            case Yekaterinburg -> "3";
        };
    }

    protected String getHabrId() {
        return switch (this) {
            case Moscow -> "678";
            case StPetersburg -> "679";
            case Yekaterinburg -> "693";
        };
    }

     protected String getName() {
        return switch (this) {
            case Moscow -> "Москва";
            case StPetersburg -> "Санкт-Петербург";
            case Yekaterinburg -> "Екатеригбург";
        };
    }


}
