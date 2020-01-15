package com.example.shell.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.shell.model.Talk;

public class MockTalkService implements TalkService {

	private List<Talk> talks = new ArrayList<>();	
	private List<Talk> presets = new ArrayList<>();
	
	private boolean isAsking = false;
	private boolean isAnswering = false;
	private boolean reverseMode = false;
	private Talk latest = null;
	
	@Override
	public void talk(String message) {
        Random r = new Random();
		this.isAnswering = false;

		// 質問に対する回答
		if (this.isAsking) {
			this.latest.setAnswer(message);
			// TODO enum で状態管理
			this.isAsking = false;
			this.isAnswering = true;
			return;
		}
		
    	// 今まで話したことがあるかどうか確認、なければ質問モードに
		this.latest = null;
		this.isAsking = false;
		for (Talk t : talks) {
			if (t.getMessage().equals(message)) {
				this.latest = t;
				this.reverseMode = false;
				break;
			}
			if (t.getAnswer() != null && t.getAnswer().equals(message)) {
				this.latest = t;
				this.reverseMode = true;
				break;
			}
		}

		if (this.latest == null) {
			// 一定確率で質問モードに
			if (r.nextBoolean()) {
				this.isAsking = true;				
			}
			Talk talk = new Talk();
			talk.setMessage(message);
			create(talk);
			this.latest = talk;
			return;
		} 
		
        if (this.latest.getAnswer() != null) {
    		// 話した内容でかつ回答があれば、一定確率(50%)で回答する
        	if (r.nextBoolean()) {
            	this.isAnswering = true;
        	}
        } else {
    		// 話した内容でかつ回答がなければ、一定確率(50%)で質問する
        	if (r.nextBoolean()) {
            	this.isAsking = true;
        	}        	
        }
	}	
	
	@Override
	public Talk create(Talk talk) {
		talk.setId(new Long(getNextId()));
		talks.add(talk);
		return talk;
	}
	
    @Override
    public List<Talk> findAllTalks() {
        return talks;
    }	

	private long getNextId() {
		long maxId = 0;
		for (Talk talk : talks) {
			if (talk.getId().longValue() > maxId) {
				maxId = talk.getId().longValue();
			}
		}
		for (Talk talk : presets) {
			if (talk.getId().longValue() > maxId) {
				maxId = talk.getId().longValue();
			}
		}		
		return maxId + 1;
	}

	@Override
	public String reply() {
    	// 質問モード
		if (this.isAsking) {
			return this.latest.getMessage() + " とは何ですか？";
		}
		
    	// 回答モード
		if (this.isAnswering) {
			if (this.reverseMode) {
				return this.latest.getAnswer() + " とは "
						+ this.latest.getMessage() + " ですね";
			} else {
				return this.latest.getMessage() + " とは "
						+ this.latest.getAnswer() + " ですね";				
			}
		}
	
		// 一定確率(75%)でプリセットを返すかどうか決める
		Talk reply = null;
        Random r = new Random();
        if (r.nextInt(4) > 1) {
            reply = talks.get(r.nextInt(talks.size()));        	
        } else {
            reply = presets.get(r.nextInt(presets.size()));
        }

        return reply.getMessage();
	}

	public void init() {
		this.presets.add(createPreset("今日はいい天気ですね"));
		this.presets.add(createPreset("私は元気です"));
		this.presets.add(createPreset("Spring結構好きです"));
		this.presets.add(createPreset("独り言雄三は心の師匠"));
		this.presets.add(createPreset("Ruby on Railsには独自の心地よさがありますね"));
	}

	private Talk createPreset(String message) {
		Talk talk = new Talk();
		talk.setId(new Long(getNextId()));
		talk.setMessage(message);
		return talk;
	}

	@Override
	public List<Talk> findAllPresets() {
        return presets;
	}
}
