import com.example.rabbitmq.compoent.rabbitmq.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class test {
    @Test
    public void main() throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnect();
        Channel channel = connection.createChannel();
        channel.queueDeclare("orginQueue", false, false, false, null);

        //生产者调用 confirmSelect 将 channel 设置为 confirm 模式注意
        channel.confirmSelect();

        String msgString = "hello confirm message !";
        channel.basicPublish("", "orginQueue", null, msgString.getBytes());

        if(!channel.waitForConfirms()){

            System.out.println("message send failed !");
        }
        else{

            System.out.println("message send ok !");
        }

        channel.close();
        connection.close();
    }
}

