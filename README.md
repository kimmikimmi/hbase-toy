# hbase-toy
set-up and practice!

hbase 의 기본 동작원리를 이해, 정리, 실습해보기 위한 toy project 


3.1 일반 정보 
HBase 에 접근하는 주요 클라이언트 인터페이스는 org.paphe.hadoop.hbase.client 패키지에 있는 Htable 클래스다. 이는 HBase 에 데이터를 저장하고 반환하며, 쓸모 없는 데이터를 삭제하는 등 사용자의 작업에 필요한 모든 기능을 제공한다.



3.2 CRUD 

3.2.1 Put

*단일 PUT*
void put(Put put) throws IOException
-Put instance를 생성하기 위해서는 rowKey 를 제공해야 한다. HBase 의 로우는 고유한 로우키로 식별하는데 그 타입은 HBase에서 사용하는 값들 대부분이 가지는 byte[] 이다. 기본적으로 row key 에는 어떤 값도 사용할 수 있다.

Java의 primitive type 을 toByte() 메소드를 이용해 변경하여 삽입하면 된다.

Put 생성 후 add 를 수행해 데이터를 추가한다. add를 한 번 수행할 때마다 컬럼 하나씩 추가한다. 타임스탬프 인수와 함께 사용하면 셀 단위로 데이터를 추가할 수 있다. 만약 타임스탬프를 명시하지 않으면 Put 인스턴스는 생성자에서 받은 타임스탬프 매개변수를 사용하고 해당 컬럼의 타임스탬프를 리전서버에 위임하게 된다.

특정 셀이 존재하는지 확인하기 위해선 has 메소드를 사용하자. 이터레이트할 필요없다.