<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'SuLiNanGit',
  data() {
    return {
      url: 'https://gitee.com/sulinan/SuLiNan-Vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>