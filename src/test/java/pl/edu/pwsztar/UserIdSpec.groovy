package pl.edu.pwsztar

import spock.lang.Specification

class UserIdSpec extends Specification {

    def idList = ['97102103810', '04220656696']


    def "Should check if size is correct"() {
        given:
            UserId userId = new UserId(idList.get(0))
            UserId userId1 = new UserId(idList.get(1))

        when:
            def answer = [userId.isCorrectSize(),
                          userId1.isCorrectSize()]
        then:
            answer == [true,true]
    }

    def "Should check if sex is valid"(){
        given:
            UserId userId = new UserId(idList.get(0))
            UserId userId1 = new UserId(idList.get(1))

        when:
            def answer = [(userId.getSex().toString() == "Optional[MAN]"),
                          userId1.getSex().toString() == "Optional[WOMAN]"]
        then:
            answer == [true, false]
    }

    def "Should check if id is correct"(){
        given:
            UserId userId = new UserId(idList.get(0))
            UserId userId1 = new UserId(idList.get(1))
        when:
            def answer = [userId.isCorrect(),
                          userId1.isCorrect()]
        then:
            answer == [true,true]
    }

    def "Should return date (dd-mm-yyyy)"(){
        given:
            UserId userId = new UserId(idList.get(0))
            UserId userId1 = new UserId(idList.get(1))
        when:
            def answer = [(userId.getDate() == Optional.of('21-10-1997')),
                          userId1.getDate() == Optional.of('06-02-2004'),]
        then:
            answer == [true, true]
    }
}
