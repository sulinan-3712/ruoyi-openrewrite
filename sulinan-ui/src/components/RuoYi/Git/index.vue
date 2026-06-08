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
      url: 'https://github.com/sulinan-3712/ruoyi-openrewrite'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
