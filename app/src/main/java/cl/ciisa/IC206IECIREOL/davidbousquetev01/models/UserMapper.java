package cl.ciisa.IC206IECIREOL.davidbousquetev01.models;

public class UserMapper {
    private IUser user;

    public UserMapper(IUser user){
        this.user = user;
    }

    public UserEntity toEntity(){
        return new UserEntity(
                this.user.getId(),
                this.user.getUsername(),
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getBirthday(),
                this.user.getHeight(),
                this.user.getPassword()
        );
    }

    public User toBase(){
        User userBase = new User(
                this.user.getUsername(),
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getBirthday(),
                this.user.getHeight()
        );
        userBase.setPassword(this.user.getPassword());
        userBase.setId(this.user.getId());
        return userBase;
    }
}

