<template>
     <EmojiPicker @select="showEmoji" />
</template>
<script>
import { ref } from "vue";
import EmojiPicker from 'vue3-emoji-picker'
import '../../../../../../node_modules/vue3-emoji-picker/dist/style.css'
import store from '../../../../../store/index';
export default {
    components: {
    EmojiPicker: EmojiPicker,
  },
  props: {
    native: {
  type: Boolean,
  default: false,
},
hideSearch: {
  type: Boolean,
  default: false,
},
hideGroupIcons: {
  type: Boolean,
  default: false,
},
hideGroupNames: {
  type: Boolean,
  default: false,
},
staticTexts: {
  type: Object,
  default: () => ({}),
},
disableStickyGroupNames: {
  type: Boolean,
  default: false,
},
disabledGroups: {
  type: Array,
  default: () => [],
},
groupNames: {
  type: Object,
  default: () => ({}),
},
disableSkinTones: {
  type: Boolean,
  default: false,
},
text: {
  type: String,
  default: '',
},
mode: {
  type: String,
  default: 'insert', // or append, prepend
},
offset: {
  type: Number,
  default: 6,
},
pickerType: {
  type: String,
  default: '',
},
  },
    setup() {
        const input = ref("");
    function onSelectEmoji(emoji) {
      input.value += emoji.i;
    }
    function showEmoji(emoji) {
    //   alert(`emoji ${emoji.i} selected, check console for details`);
      console.log(emoji);
      store.state.chat.data_input += emoji.i; 
    }
    return {
      input,
      onSelectEmoji,
      showEmoji,
    };
    },
}
</script>