package cn.chat.wechat.pojo.response;

public class VoiceMessageResp extends BaseMessageAttribute{
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		this.Voice = voice;
	}
	
}
